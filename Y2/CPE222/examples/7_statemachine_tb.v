`timescale 1ns / 1ps

module StateMachine_tb();

reg     clk;
reg     reset;
reg     start;
reg     resume;
reg     pause;
reg     die;

wire [1:0]    state_reg;        

//wire clk190, clk25, clk3;

StateMachine SM (.clk(clk), .reset(reset), .start(start), .resume(resume), .pause(pause), .die(die), .state_reg(state_reg));

initial begin
    clk = 0;
    reset = 0;
	
	#100
	reset = 1;
	
	#100
	reset = 0;
    start = 0;
    resume = 0;
    pause = 0;
    die = 0;

    #100
    pause = 1;
    #100
    pause = 0;
    resume = 1;
    #100
    resume = 0;
    die = 1;
    #50
    die = 0;
    start = 1;
    #50
    start = 0;
    pause = 1;
    #100
    pause = 0;
    reset = 1;
end 

always begin 
    #10 clk = ~clk;
end 

endmodule




















