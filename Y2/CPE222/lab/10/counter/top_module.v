module top_module(
    input clk,
    input clear,
    output [6:0] seg
);

wire clk_1hz;
wire [3:0] bcd_count;

divider u_divider (
    .rst_ni(clear),
    .clk_i(clk),
    .clk_o(clk_1hz)
);

counter u_counter (
    .clk_in(clk_1hz),
    .sw(clear),
    .led(bcd_count)
);

bcdto7seg u_bcdto7seg (
    .led(bcd_count),
    .seg(seg)
);

endmodule
