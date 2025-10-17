`timescale 1ns / 1ps

module logic_gate_tb();

reg clr, clk;
wire [7:0] q;

counter #(.N(8))  cnt16 (.clr(clr),.clk(clk),.q(q));

initial begin
    clr = 0;
	clk = 0;
	#10
	clr = 1;
	#10
	clr = 0;
end 

always begin 
    #10 clk = ~clk;
end 
 
initial begin
    $display(" clr clk q");
    $monitor("\t%b \t%b \t%b", clr, clk, q);
end

endmodule




















