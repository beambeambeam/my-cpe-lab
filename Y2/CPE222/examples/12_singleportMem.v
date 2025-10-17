module singleportmem
	(input clk,
	input reset,
	inout [15:0] data_io, //new I/O type
	input [9:0] address,
	input wr_en,
	input rd
	);

	reg [15:0] memory[0:1023];
	reg [15:0] data_out;
	reg rd_dl;
	
	always @( posedge clk ) begin
		if (wr_en)
			memory[address] <= data_io;
		data_out <= memory[address];
		rd_dl <= rd;
	end
	assign data_io = rd_dl ? data_out : 16'bz;
	
endmodule