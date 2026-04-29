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
class AnimationSpec:
    output_name: str
    slide_title: str
    pass_labels: list[str]
    states: list[list[str]]
    summary: str


def addr_to_name(addr: int) -> str:
    return f"A(0,{addr // 4})"


def _snapshot_fully_associative(cache: list[int]) -> list[str]:
    values = [addr_to_name(addr) for addr in cache]
    return values + [""] * (8 - len(values))


def _snapshot_set_associative(set_zero: list[int]) -> list[str]:
    values = [addr_to_name(addr) for addr in set_zero]
    return values + [""] * (4 - len(values)) + [""] * 4


def _simulate_fully_associative() -> tuple[list[list[str]], list[list[str]]]:
    cache: list[int] = []
    first_loop: list[list[str]] = []
    second_loop: list[list[str]] = []

    for addr in ADDRESSES:
        if addr in cache:
            cache.remove(addr)
        cache.insert(0, addr)
        if len(cache) > 8:
            cache.pop()
        first_loop.append(_snapshot_fully_associative(cache))

    for addr in reversed(ADDRESSES):
        if addr in cache:
            cache.remove(addr)
        cache.insert(0, addr)
        if len(cache) > 8:
            cache.pop()
        second_loop.append(_snapshot_fully_associative(cache))

    return first_loop, second_loop


def _simulate_set_associative() -> tuple[list[list[str]], list[list[str]]]:
    set_zero: list[int] = []
    first_loop: list[list[str]] = []
    second_loop: list[list[str]] = []

    for addr in ADDRESSES:
        if addr in set_zero:
            set_zero.remove(addr)
        set_zero.insert(0, addr)
        if len(set_zero) > 4:
            set_zero.pop()
        first_loop.append(_snapshot_set_associative(set_zero))

    for addr in reversed(ADDRESSES):
        if addr in set_zero:
            set_zero.remove(addr)
        set_zero.insert(0, addr)
        if len(set_zero) > 4:
            set_zero.pop()
        second_loop.append(_snapshot_set_associative(set_zero))

    return first_loop, second_loop


def build_specs() -> dict[str, AnimationSpec]:
    fa_first, fa_second = _simulate_fully_associative()
    sa_first, sa_second = _simulate_set_associative()

    return {
        "figure_1": AnimationSpec(
            output_name="figure_1.mp4",
            slide_title="Content of data cache after pass:",
            pass_labels=[f"j = {idx}" for idx in range(10)],
            states=fa_first,
            summary="Associative mapping, first loop",
        ),
        "figure_2": AnimationSpec(
            output_name="figure_2.mp4",
            slide_title="Content of data cache after pass:",
            pass_labels=[f"i = {idx}" for idx in range(9, -1, -1)],
            states=fa_second,
            summary="Associative mapping, second loop",
        ),
        "figure_3": AnimationSpec(
            output_name="figure_3.mp4",
            slide_title="Content of data cache after pass:",
            pass_labels=[f"j = {idx}" for idx in range(10)],
            states=sa_first,
            summary="2-way set associative mapping, first loop",
        ),
        "figure_4": AnimationSpec(
            output_name="figure_4.mp4",
            slide_title="Content of data cache after pass:",
            pass_labels=[f"i = {idx}" for idx in range(9, -1, -1)],
            states=sa_second,
            summary="2-way set associative mapping, second loop",
        ),
    }


def _draw_frame(spec: AnimationSpec, frame_index: int):
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
    table_w = col_w * len(spec.pass_labels)
    current = frame_index

    ax.add_patch(Rectangle((x0, y_top - title_h), table_w, title_h, fill=False, linewidth=1.6, edgecolor="black"))
    ax.text(x0 + table_w / 2, y_top - title_h / 2, spec.slide_title, ha="center", va="center", fontsize=22)

    header_y = y_top - title_h - header_h
    for col, label in enumerate(spec.pass_labels):
        x = x0 + col * col_w
        ax.add_patch(Rectangle((x, header_y), col_w, header_h, fill=False, linewidth=1.1, edgecolor="black"))
        if col <= current:
            color = "red" if col == current else "black"
            weight = "bold" if col == current else "normal"
            ax.text(x + col_w / 2, header_y + header_h / 2, label, ha="center", va="center", fontsize=16, color=color, fontweight=weight)

    data_top = header_y
    ax.text(1.75, header_y + header_h * 0.6, "Block\nposition", ha="center", va="center", fontsize=18)
    for row in range(8):
        row_y = data_top - (row + 1) * row_h
        ax.text(2.0, row_y + row_h / 2, str(row), ha="right", va="center", fontsize=16)
        for col in range(len(spec.pass_labels)):
            x = x0 + col * col_w
            ax.add_patch(Rectangle((x, row_y), col_w, row_h, fill=False, linewidth=1.0, edgecolor="black"))
            if col <= current:
                value = spec.states[col][row]
                if value:
                    ax.text(
                        x + col_w / 2,
                        row_y + row_h / 2,
                        value,
                        ha="center",
                        va="center",
                        fontsize=13,
                        color="red" if col == current else "black",
                        fontweight="bold" if col == current else "normal",
                    )

    ax.text(8.0, 0.7, spec.summary, ha="center", va="center", fontsize=18)
    fig.tight_layout(pad=0)
    fig.canvas.draw()
    width, height = fig.canvas.get_width_height()
    image = memoryview(fig.canvas.buffer_rgba()).tobytes()
    frame = np.frombuffer(image, dtype=np.uint8).reshape((height, width, 4))[:, :, :3].copy()
    plt.close(fig)
    return frame


def render_animation(spec: AnimationSpec, output_dir: Path, fps: int = 2) -> Path:
    output_dir.mkdir(parents=True, exist_ok=True)
    frames = []
    for idx in range(len(spec.pass_labels)):
        frame = _draw_frame(spec, idx)
        repeat = fps if idx < len(spec.pass_labels) - 1 else fps * 2
        frames.extend([frame] * repeat)

    output_path = output_dir / spec.output_name
    imageio.mimsave(output_path, frames, fps=fps, codec="libx264", quality=8, macro_block_size=1)
    return output_path


def run(name: str) -> Path:
    specs = build_specs()
    return render_animation(specs[name], Path(__file__).resolve().parent / "output")
