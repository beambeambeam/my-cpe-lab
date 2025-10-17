`timescale 1ns / 1ps


module vga_basic(
	input clk,
	output HS,
	output VS,
	output [3:0] RED,
	output [3:0] GREEN,
	output [3:0] BLUE
	);

	wire [9:0] x, y;
	vga v(.clk (clk), .HS (HS), .VS (VS), .x (x), .y (y));

	assign RED = ((x > 0) & (x < 300) & (y > 0) & (y < 300))?7:0;
	assign GREEN = ((x > 200) & (x < 400) & (y > 150) & (y < 350)?7:0);
	assign BLUE = ((x > 300) & (x < 600) & (y > 180) & (y < 480))?3:0;
	
endmodule