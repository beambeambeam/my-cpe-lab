`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date: 09/21/2022 12:08:54 PM
// Design Name:
// Module Name: lab09_tb
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


module lab09_tb();

reg [2:0] inTb;
wire yTb;

top dut(.in(inTb), .y(yTb));

initial begin
    inTb = 3'b000;
    #10;
end

initial begin
    repeat(8) #10 inTb = inTb + 1'b1;
end

initial begin
    $display(" SW0 SW1 SW2  LD0");
    $monitor("\t%b \t%b \t%b \t%b", inTb[0], inTb[1], inTb[2], yTb);
end

endmodule
