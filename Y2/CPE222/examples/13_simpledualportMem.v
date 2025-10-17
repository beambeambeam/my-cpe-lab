module simpledpmem
	#(
        parameter DATA_SIZE = 16,
        parameter ADDR_SIZE = 16
    )
	(input clk,
	input reset,
	input [DATA_SIZE-1:0] dat_in,
	input [ADDR_SIZE-1:0] wr_adr,
	input wr_en,
	output reg [DATA_SIZE-1:0] dat_out,
	input [ADDR_SIZE-1:0] rd_adr
	);

	reg [DATA_SIZE-1:0] memory[2**ADDR_SIZE-1:0];

	always @( posedge clk ) begin
		if (wr_en)
			memory[wr_adr] <= dat_in;
		dat_out <= memory[rd_adr];
	end
	

endmodule