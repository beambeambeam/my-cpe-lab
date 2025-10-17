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

reg [3:0] in_c;
reg [1:0] in_s;

wire out_z;

mux41c M1 (.c(in_c), .s(in_s), .z(out_z));

initial begin
    in_c = 4'b0101;
    in_s = 0;
    
    #100;
    in_s = in_s + 1;
    #100;
    in_s = in_s + 1;
	#100;
    in_s = in_s + 1;
    #100;
	in_s = in_s + 1;
	#100;
    in_s = in_s + 1;
    #100;
	in_s = in_s + 1;
end 

initial begin
    $display(" in_c in_s out_z");
    $monitor("\t%b \t%b \t%b \t%b", in_c, in_s,out_z);
end

endmodule




















