`timescale 1ns / 1ps


module counter
	#(parameter N = 4)(
	input wire clr ,
	input wire clk ,
	output reg [N-1:0] q
	);
	// N-bit counter
	always @(posedge clk or posedge clr) begin
		if(clr == 1)
			q <= 0;
		else
			q <= q + 1;
	end
endmodule