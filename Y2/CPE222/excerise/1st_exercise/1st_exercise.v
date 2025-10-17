`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date:
// Design Name:
// Module Name: first_exercise
// Project Name:
// Target Devices:
// Tool Versions:
// Description: Logic gate circuit implementation
//              Q = (A AND B) OR (A AND (NOT B OR NOT C))
//
// Dependencies:
//
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
//
//////////////////////////////////////////////////////////////////////////////////


module first_exercise(A, B, C, Q);

input A, B, C;
output Q;

// Internal wires for intermediate signals
wire and1_out;        // A AND B
wire not_b;           // NOT B
wire not_c;           // NOT C
wire or1_out;         // NOT B OR NOT C
wire and2_out;        // A AND (NOT B OR NOT C)

// Implement the logic circuit as described
assign and1_out = A & B;                    // A AND B
assign not_b = ~B;                          // NOT B
assign not_c = ~C;                          // NOT C
assign or1_out = not_b | not_c;             // NOT B OR NOT C
assign and2_out = A & or1_out;              // A AND (NOT B OR NOT C)
assign Q = and1_out | and2_out;             // (A AND B) OR (A AND (NOT B OR NOT C))

endmodule
