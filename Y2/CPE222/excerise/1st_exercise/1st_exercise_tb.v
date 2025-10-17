`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date:
// Design Name:
// Module Name: first_exercise_tb
// Project Name:
// Target Devices:
// Tool Versions:
// Description: Testbench for first_exercise module
//
// Dependencies:
//
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
//
//////////////////////////////////////////////////////////////////////////////////


module first_exercise_tb();

reg A_t, B_t, C_t;
wire Q_t;

first_exercise M1 (.A(A_t), .B(B_t), .C(C_t), .Q(Q_t));

initial begin
    A_t = 0;
    B_t = 0;
    C_t = 0;

    #100;

    repeat (8)
    #200 {A_t, B_t, C_t} = {A_t, B_t, C_t} + 1'b1;
end

initial begin
    $display(" A B C Q");
  $dumpfile("dump.vcd"); $dumpvars(1,first_exercise_tb);
    $monitor("\t%b \t%b \t%b \t%b", A_t, B_t, C_t, Q_t);
end

endmodule
