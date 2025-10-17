`timescale 1ns / 1ps


module blocking (
	input wire clk ,
	output reg q
	);
	
reg m, n, o;

	always @(posedge clk) begin
		m = 1;
		n = m;
		o = n;
		q = o;
	end
endmodule