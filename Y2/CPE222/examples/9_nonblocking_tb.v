`timescale 1ns / 1ps

module logic_gate_tb();

reg clk;
wire q;

nonblocking B1 (.clk(clk),.q(q));

initial begin
	clk = 0;
end 

always begin 
    #10 clk = ~clk;
end 
 
initial begin
    $display(" clk q");
    $monitor("\t%b \t%b ", clk, q);
end

endmodule




















