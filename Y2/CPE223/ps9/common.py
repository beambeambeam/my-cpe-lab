from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path

import imageio.v2 as imageio
import matplotlib
import numpy as np

matplotlib.use("Agg")
import matplotlib.pyplot as plt
from matplotlib.patches import Rectangle


ADDRESSES = [0, 4, 8, 12, 16, 20, 24, 28, 32, 36]


@dataclass(frozen=True)
class PassEvent:
    label: str
    access_name: str
    hit: bool
    before: list[str]
    after: list[str]
    source_row: int | None
    evicted_name: str | None
    target_rows: list[int]


@dataclass(frozen=True)
class AnimationSpec:
    output_name: str
    slide_title: str
    summary: str
    events: list[PassEvent]


@dataclass(frozen=True)
class MicroFrame:
    event_index: int
    phase: str
    filled_rows: int


def addr_to_name(addr: int) -> str:
    return f"A(0,{addr // 4})"


def _snapshot_full(cache: list[int]) -> list[str]:
    values = [addr_to_name(addr) for addr in cache]
    return values + [""] * (8 - len(values))


def _snapshot_set_zero(cache: list[int]) -> list[str]:
    values = [addr_to_name(addr) for addr in cache]
    return values + [""] * (4 - len(values)) + [""] * 4


def _target_rows(before: list[str], after: list[str]) -> list[int]:
    return [row for row, (old, new) in enumerate(zip(before, after)) if old != new]


def _event_note(event: PassEvent) -> str:
    if event.hit:
        return f"Hit: {event.access_name} found in row {event.source_row} and moved to MRU position."
    if event.evicted_name:
        return f"Miss: insert {event.access_name} and evict {event.evicted_name} from the LRU row."
    return f"Miss: insert {event.access_name} into the next available cache row."


def _build_full_events(labels: list[str], addresses: list[int], initial_cache: list[int] | None = None) -> list[PassEvent]:
    cache: list[int] = list(initial_cache or [])
    before_state = _snapshot_full(cache)
    events: list[PassEvent] = []

    for label, addr in zip(labels, addresses):
        access_name = addr_to_name(addr)
        source_row = before_state.index(access_name) if access_name in before_state else None
        hit = addr in cache
        evicted_name = addr_to_name(cache[-1]) if (not hit and len(cache) == 8) else None

        if hit:
            cache.remove(addr)
        cache.insert(0, addr)
        if len(cache) > 8:
            cache.pop()

        after_state = _snapshot_full(cache)
        events.append(
            PassEvent(
                label=label,
                access_name=access_name,
                hit=hit,
                before=before_state,
                after=after_state,
                source_row=source_row,
                evicted_name=evicted_name,
                target_rows=_target_rows(before_state, after_state),
            )
        )
        before_state = after_state

    return events


def _build_set_events(labels: list[str], addresses: list[int], initial_set_zero: list[int] | None = None) -> list[PassEvent]:
    set_zero: list[int] = list(initial_set_zero or [])
    before_state = _snapshot_set_zero(set_zero)
    events: list[PassEvent] = []

    for label, addr in zip(labels, addresses):
        access_name = addr_to_name(addr)
        source_row = before_state.index(access_name) if access_name in before_state else None
        hit = addr in set_zero
        evicted_name = addr_to_name(set_zero[-1]) if (not hit and len(set_zero) == 4) else None

        if hit:
            set_zero.remove(addr)
        set_zero.insert(0, addr)
        if len(set_zero) > 4:
            set_zero.pop()

        after_state = _snapshot_set_zero(set_zero)
        events.append(
            PassEvent(
                label=label,
                access_name=access_name,
                hit=hit,
                before=before_state,
                after=after_state,
                source_row=source_row,
                evicted_name=evicted_name,
                target_rows=_target_rows(before_state, after_state),
            )
        )
        before_state = after_state

    return events


def build_specs() -> dict[str, AnimationSpec]:
    first_labels = [f"j = {idx}" for idx in range(10)]
    second_labels = [f"i = {idx}" for idx in range(9, -1, -1)]
    fa_first_cache = ADDRESSES[-8:]
    sa_first_cache = ADDRESSES[-4:]

    return {
        "figure_1": AnimationSpec(
            output_name="figure_1.mp4",
            slide_title="Content of data cache after pass:",
            summary="Associative mapping, first loop",
            events=_build_full_events(first_labels, ADDRESSES),
        ),
        "figure_2": AnimationSpec(
            output_name="figure_2.mp4",
            slide_title="Content of data cache after pass:",
            summary="Associative mapping, second loop",
            events=_build_full_events(second_labels, list(reversed(ADDRESSES)), initial_cache=fa_first_cache),
        ),
        "figure_3": AnimationSpec(
            output_name="figure_3.mp4",
            slide_title="Content of data cache after pass:",
            summary="2-way set associative mapping, first loop",
            events=_build_set_events(first_labels, ADDRESSES),
        ),
        "figure_4": AnimationSpec(
            output_name="figure_4.mp4",
            slide_title="Content of data cache after pass:",
            summary="2-way set associative mapping, second loop",
            events=_build_set_events(second_labels, list(reversed(ADDRESSES)), initial_set_zero=sa_first_cache),
        ),
    }


def _build_microframes(spec: AnimationSpec) -> list[MicroFrame]:
    frames: list[MicroFrame] = []
    for event_index, event in enumerate(spec.events):
        frames.append(MicroFrame(event_index, "header", 0))
        frames.append(MicroFrame(event_index, "decision", 0))
        changed_rows = event.target_rows or [0]
        for filled_rows in range(1, len(changed_rows) + 1):
            frames.append(MicroFrame(event_index, "fill", filled_rows))
        frames.append(MicroFrame(event_index, "final", len(changed_rows)))
    return frames


def _draw_cell_text(ax, x: float, y: float, w: float, h: float, text: str, color: str = "black", weight: str = "normal"):
    ax.text(x + w / 2, y + h / 2, text, ha="center", va="center", fontsize=13, color=color, fontweight=weight)


def _draw_frame(spec: AnimationSpec, microframe: MicroFrame):
    fig, ax = plt.subplots(figsize=(16, 9), dpi=150)
    ax.set_xlim(0, 16)
    ax.set_ylim(0, 9)
    ax.axis("off")

    x0 = 3.0
    y_top = 7.9
    title_h = 0.72
    header_h = 0.9
    row_h = 0.56
    col_w = 1.15
    table_w = col_w * len(spec.events)
    current = microframe.event_index
    event = spec.events[current]

    ax.add_patch(Rectangle((x0, y_top - title_h), table_w, title_h, fill=False, linewidth=1.6, edgecolor="black"))
    ax.text(x0 + table_w / 2, y_top - title_h / 2, spec.slide_title, ha="center", va="center", fontsize=22)

    header_y = y_top - title_h - header_h
    for col, pass_event in enumerate(spec.events):
        x = x0 + col * col_w
        ax.add_patch(Rectangle((x, header_y), col_w, header_h, fill=False, linewidth=1.1, edgecolor="black"))
        if col < current:
            _draw_cell_text(ax, x, header_y, col_w, header_h, pass_event.label)
        elif col == current:
            _draw_cell_text(ax, x, header_y, col_w, header_h, pass_event.label, color="red", weight="bold")

    ax.text(1.75, header_y + header_h * 0.6, "Block\nposition", ha="center", va="center", fontsize=18)

    highlight_rows = set()
    if microframe.phase == "decision":
        if event.hit and event.source_row is not None:
            highlight_rows.add(event.source_row)
        elif not event.hit:
            if event.evicted_name and event.before.count("") == 0:
                highlight_rows.add(7 if len(event.before) == 8 else 3)
            else:
                try:
                    highlight_rows.add(event.before.index(""))
                except ValueError:
                    pass
    elif microframe.phase in {"fill", "final"}:
        changed = event.target_rows or [0]
        shown = changed[: microframe.filled_rows] if microframe.phase == "fill" else changed
        highlight_rows.update(shown)

    highlight_color = "#d9f2d9" if event.hit else "#ffe0e0"

    for row in range(8):
        row_y = header_y - (row + 1) * row_h
        ax.text(2.0, row_y + row_h / 2, str(row), ha="right", va="center", fontsize=16)
        for col, pass_event in enumerate(spec.events):
            x = x0 + col * col_w
            facecolor = "white"
            if col == current and row in highlight_rows:
                facecolor = highlight_color
            ax.add_patch(Rectangle((x, row_y), col_w, row_h, facecolor=facecolor, fill=True, linewidth=1.0, edgecolor="black"))

            text = ""
            color = "black"
            weight = "normal"

            if col < current:
                text = pass_event.after[row]
            elif col == current:
                if microframe.phase in {"header", "decision"}:
                    text = ""
                elif microframe.phase == "fill":
                    changed = event.target_rows or [0]
                    shown_rows = set(changed[: microframe.filled_rows])
                    if row in shown_rows:
                        text = event.after[row]
                        color = "red"
                        weight = "bold"
                else:
                    text = event.after[row]
                    if text and row in (event.target_rows or [0]):
                        color = "red"
                        weight = "bold"

            if text:
                _draw_cell_text(ax, x, row_y, col_w, row_h, text, color=color, weight=weight)

    status = "CACHE HIT" if event.hit else "CACHE MISS"
    status_color = "#2e7d32" if event.hit else "#c62828"
    ax.text(8.0, 1.15, status, ha="center", va="center", fontsize=24, color=status_color, fontweight="bold")
    ax.text(8.0, 0.82, f"Current pass: {event.label}   Access: {event.access_name}", ha="center", va="center", fontsize=17)
    ax.text(8.0, 0.47, _event_note(event), ha="center", va="center", fontsize=15, color=status_color)
    ax.text(8.0, 0.15, spec.summary, ha="center", va="center", fontsize=16)

    fig.tight_layout(pad=0)
    fig.canvas.draw()
    width, height = fig.canvas.get_width_height()
    image = memoryview(fig.canvas.buffer_rgba()).tobytes()
    frame = np.frombuffer(image, dtype=np.uint8).reshape((height, width, 4))[:, :, :3].copy()
    plt.close(fig)
    return frame


def render_animation(spec: AnimationSpec, output_dir: Path, fps: int = 3) -> Path:
    output_dir.mkdir(parents=True, exist_ok=True)
    microframes = _build_microframes(spec)
    frames = [_draw_frame(spec, microframe) for microframe in microframes]
    output_path = output_dir / spec.output_name
    imageio.mimsave(output_path, frames, fps=fps, codec="libx264", quality=8, macro_block_size=1)
    return output_path


def run(name: str) -> Path:
    specs = build_specs()
    return render_animation(specs[name], Path(__file__).resolve().parent / "output")
