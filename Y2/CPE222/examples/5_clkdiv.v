module clkdiv (
	input wire clk ,
	input wire clr ,
	output wire clk190 ,
	output wire clk25 ,
	output wire clk3
);
	reg [23:0] q;
	// 24-bit counter
	always @(posedge clk or posedge clr) begin
		if(clr == 1)
		q <= 0;
		else
		q <= q + 1;
	end
	
	assign clk190 = q[17]; // 190 Hz
	assign clk25 = q[0]; // 25 MHz
	assign clk3 = q[23]; // 3 Hz
	
endmodule