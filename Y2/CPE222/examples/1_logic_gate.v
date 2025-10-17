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


module logic_gate(out1, out2, in1, in2);

input in1, in2;
output out1, out2;

wire and_out, or_out;

assign and_out = in1 & in2;
assign or_out = in1 | in2;
assign out1 = and_out ^ or_out;
assign out2 = ~in2;



endmodule
