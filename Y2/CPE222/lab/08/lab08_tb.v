`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date:
// Design Name:
// Module Name: lab08_tb
// Project Name:
// Target Devices:
// Tool Versions:
// Description: Testbench for lab08 module
//
// Dependencies:
//
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
//
//////////////////////////////////////////////////////////////////////////////////


module lab08_tb();

reg A_t, B_t, C_t, D_t;
wire F_t;

lab08 M1 (.A(A_t), .B(B_t), .C(C_t), .D(D_t), .F(F_t));

initial begin
    A_t = 0;
    B_t = 0;
    C_t = 0;
    D_t = 0;

    #100;

    repeat (16)
    #200 {A_t, B_t, C_t, D_t} = {A_t, B_t, C_t, D_t} + 1'b1;
end

initial begin
    $display(" A B C D F");
  $dumpfile("dump.vcd"); $dumpvars(1,lab08_tb);
    $monitor("\t%b \t%b \t%b \t%b \t%b", A_t, B_t, C_t, D_t, F_t);
end

endmodule
