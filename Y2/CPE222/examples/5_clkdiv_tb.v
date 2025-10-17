`timescale 1ns / 1ps

module logic_gate_tb();

reg clr, clk;
wire clk190, clk25, clk3;

clkdiv U1 (.clr(clr),.clk(clk),.clk190(clk190)
			,.clk25(clk25),.clk3(clk3));

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
    $display(" clr clk clk190 clk25 clk3");
    $monitor("\t%b \t%b \t%b \t%b \t%b", 
			clr, clk, clk190, clk25, clk3);
end

endmodule




















