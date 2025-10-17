`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 09/21/2022 12:08:54 PM
// Design Name: 
// Module Name: logic_gate_tb
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


module logic_gate_tb();

reg in_a, in_b, in_s;

wire out_y;

mux21b M1 (.a(in_a), .b(in_b), .s(in_s), .y(out_y));

initial begin
    in_a = 1;
    in_b = 0;
    in_s = 1;
    
    #100;
    in_s = 0;
    #100;
    in_s = 1;
end 

initial begin
    $display(" in_a in_b in_s out_y");
    $monitor("\t%b \t%b \t%b \t%b", in_a,in_b,in_s,out_y);
end

endmodule




















