`timescale 1ns / 1ps


module vga_moveRect(
	input clk,
	output HS,
	output VS,
	output [3:0] RED,
	output [3:0] GREEN,
	output [3:0] BLUE
	);

	wire [9:0] x, y;
	wire blank;
	
	reg [11:0] mem[76799:0];  // 320x240
	wire [16:0] mem_index;
	reg [11:0] color;

	initial begin
		$readmemh("bg1.mem", mem);
	end
	
	vga v(.clk (clk), .HS (HS), .VS (VS), .x (x), .y (y), .blank (blank));
	
	assign mem_index = (y / 2) * 320 + x / 2; 	
	assign RED = (blank?0:color[11:8]);
	assign GREEN = (blank?0:color[7:4]);
	assign BLUE = (blank?0:color[3:0]);
	
	always @(posedge clk) begin
	   color <= mem[mem_index];
	end
endmodule