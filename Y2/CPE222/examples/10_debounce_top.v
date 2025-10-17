`timescale 1ns / 1ps

module debounce_top(
	input clk,
	input switch_a,
	input switch_b,
	output reg led_a,
	output reg led_b,
	output reg led_c
);

	wire s_a_dn, s_b_up, s_a_state;
	debouncer d1(.clk (clk), .PB(switch_a),.PB_down (s_a_dn));
	debouncer d2(.clk (clk), .PB (switch_b),.PB_up (s_b_up));
	debouncer d3(.clk (clk), .PB (switch_a),.PB_state (s_a_state));

	always @(posedge clk) begin
		if (s_a_dn) begin
			led_a <= ~ led_a;
		end
		if (s_b_up) begin
			led_b <= ~ led_b;
		end
		led_c <= s_a_state;
	end
	
endmodule















