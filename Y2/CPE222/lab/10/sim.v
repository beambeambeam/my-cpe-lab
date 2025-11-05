`timescale 1ns / 1ps

module tb_clock_divider;

reg clock_in;
reg rst_ni;
wire clock_out;

clk_div uut (
  .clk_i(clock_in),
  .rst_ni(rst_ni),
  .clk_o(clock_out)
);

initial begin
  $dumpvars(1, tb_clock_divider);
  $dumpfile("waveform.vcd");
  clock_in = 0;
  rst_ni = 0;
  #10;
  rst_ni = 1;
end

initial begin
  forever #5 clock_in = ~clock_in;
end

initial begin
  $display("Time   rst_ni  clk_i  clk_o");
  $monitor("%4d   %b      %b     %b", $time, rst_ni, clock_in, clock_out);
end

initial #1000000 $finish;

endmodule
