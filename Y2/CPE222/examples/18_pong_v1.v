`timescale 1ns / 1ps


module pong(
	input clk,
	input btn_reset,
	input btn_up,
	input btn_dn,
	output HS,
	output VS,
	output [3:0] RED,
	output [3:0] GREEN,
	output [3:0] BLUE
	);

	wire [9:0] x, y;
	wire blank;
	reg [11:0] color;
	
	vga v(.clk (clk), .HS (HS), .VS (VS), .x (x), .y (y), .blank(blank));
	
	// obj representation
	localparam BALL_SIZE  =  8;  // ball size in pixels
	localparam BALL_ISPX  =  5;  // initial horizontal ball speed
    localparam BALL_ISPY  =  3;  // initial vertical ball speed
	localparam PAD_HEIGHT = 48;  // paddle height in pixels
    localparam PAD_WIDTH  = 10;  // paddle width in pixels
    localparam PAD_OFFS   = 32;  // paddle distance from edge of screen in pixels
    localparam PAD_SPY    =  3;  // vertical paddle speed
	localparam SPEEDUP    =  5;  // speed up ball after this many shots (max 16)
	
	reg ball, padl, padr;		
	reg [9:0] ball_x, ball_y;		// position (origin at top left)
	reg [9:0] ball_spx, ball_spy;
	reg ball_dx, ball_dy;			// direction: 0 is right/down
	reg coll_l, coll_r;
	reg [9:0] padl_y, padr_y;		// vertical position of left and right paddles

	// game state
	parameter NEW_GAME=2'b00, PLAY=2'b01;
	reg [1:0] state = NEW_GAME;
	
	// game logic
	always @(posedge VS) begin
		case (state)
			NEW_GAME: begin
				
				ball_y <= (480 - BALL_SIZE)/2;
				ball_x <= (640 - BALL_SIZE)/2;

			end
			
			PLAY: begin
				
			end
		endcase
		
		if (btn_reset) state <= NEW_GAME;
	end
	
		// Player paddle control
	always @(posedge VS) begin
	
		if (btn_dn) begin
			if (padl_y + PAD_HEIGHT + PAD_SPY >= 480-1) begin  // bottom of screen?
				padl_y <= 480 - PAD_HEIGHT - 1;  // move down as far as we can
			end else padl_y <= padl_y + PAD_SPY;  // move down
		end else if (btn_up) begin
			if (padl_y < PAD_SPY) begin  // top of screen
				padl_y <= 0;  // move up as far as we can
			end else padl_y <= padl_y - PAD_SPY;  // move up
		end
		
	end


	// render obj bbox
	always @(*) begin
        ball = (x >= ball_x) && (x < ball_x + BALL_SIZE)
               && (y >= ball_y) && (y < ball_y + BALL_SIZE);
        padl = (x >= PAD_OFFS) && (x < PAD_OFFS + PAD_WIDTH)
               && (y >= padl_y) && (y < padl_y + PAD_HEIGHT);
        padr = (x >= 640 - PAD_OFFS - PAD_WIDTH - 1) && (x < 640 - PAD_OFFS - 1)
               && (y >= padr_y) && (y < padr_y + PAD_HEIGHT);
    end
	
	
	// set obj color
	always @(*) begin
		if (ball) color[11:0] = 12'h0F0;
		else if (padl || padr) color[11:0] = 12'hFFF;
		else color[11:0] = 12'h000;
	end
	
	
	assign RED = (blank?0:color[11:8]);
	assign GREEN = (blank?0:color[7:4]);
	assign BLUE = (blank?0:color[3:0]);
	
endmodule