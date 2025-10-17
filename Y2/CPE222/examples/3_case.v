`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 09/21/2022 12:03:11 PM
// Design Name: 
// Module Name: logic_gate
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module mux41c (
	input wire [3:0] c ,
	input wire [1:0] s ,
	output reg z
);
	always @(*)
		case(s)
			0: z = c[0];
			1: z = c[1];
			2: z = c[2];
			3: z = c[3];
			default: z = c[0];
		endcase
endmodule