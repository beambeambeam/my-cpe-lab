`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date:
// Design Name:
// Module Name: lab08
// Project Name:
// Target Devices:
// Tool Versions:
// Description: Logic gate circuit implementation
//              F = A(~B+D+C) = A AND (NOT B OR D OR C)
//
// Dependencies:
//
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
//
//////////////////////////////////////////////////////////////////////////////////


module lab08(A, B, C, D, F);

input A, B, C, D;
output F;

wire not_b;           // NOT B
wire or1_out;         // NOT B OR D OR C

assign not_b = ~B;                          // NOT B
assign or1_out = not_b | D | C;             // NOT B OR D OR C
assign F = A & or1_out;                     // A AND (NOT B OR D OR C)

endmodule
