module comp
	#(parameter N = 8)
	(input wire [N-1:0] x,
	input wire [N-1:0] y,
	output reg gt,
	output reg eq,
	output reg lt
);
	always @(*) begin
		gt = 0;
		eq = 0;
		lt = 0;
		if(x > y)
			gt = 1;
		if(x == y)
			eq = 1;
		if(x < y)
			lt = 1;
	end
endmodule