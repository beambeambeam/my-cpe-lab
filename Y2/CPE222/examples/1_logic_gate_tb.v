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

reg in1t, in2t;

wire out1t, out2t;

logic_gate M1 (.out1(out1t),.out2(out2t),.in1(in1t),.in2(in2t));

initial begin
    in1t = 0;
    in2t = 0;
    
    #100;
    
    repeat (4)
    #200 {in1t,in2t} = {in1t,in2t} + 1'b1;
end 

initial begin
    $display(" in1 in2 out1 out2");
    $monitor("\t%b \t%b \t%b \t%b", in1t,in2t,out1t,out2t);
end

endmodule




















