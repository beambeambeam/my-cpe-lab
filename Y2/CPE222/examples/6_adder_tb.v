`timescale 1ns / 1ps

module logic_gate_tb();

reg [3:0] a;
reg [3:0] b;
wire [3:0] y;


adder #(.N(4)) A1 (.a(a),.b(b),.y(y));

initial begin
    a = 4'b0111;
	b = 4'b0111;
	#10
	a = 4'b1010;
	b = 4'b0010;
end 

 
initial begin
    $display(" a b y");
    $monitor("\t%b \t%b \t%b",a,b,y);
end

endmodule




















