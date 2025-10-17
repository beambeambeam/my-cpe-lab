`timescale 1ns / 1ps


module movingLed_tb();
	
	reg clk;
	reg clr;
	wire [15:0] led;
	wire clk6;
	wire [3:0] counter;
	
	
movingLed mLed (.clk(clk), .clr(clr), .led(led), .clk6_out(clk6), .counter_out(counter));
	
initial begin
	clk = 0;
	clr = 0;
	
	#100 
	clr = 1;
	
	#100
	clr = 0;
	
end
	
	
always begin 
    #10 clk = ~clk;
end 

endmodule