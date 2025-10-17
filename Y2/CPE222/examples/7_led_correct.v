
module movingLed(
	input wire clk,		
	input wire clr,
	output reg [15:0] led
);
	// 24-bit counter
	reg [23:0] q;
	reg [3:0] counter;
	
	always @(posedge clk) begin
		q <= q + 1;	
		if(q[22] == 1) begin
		    q <= 0;
			counter <= counter + 1;
			led <= 1 << counter;
		end
		
		if(clr == 1) begin
			q <= 0;
			counter <= 0;
			led <= 0;
		end

	end

endmodule