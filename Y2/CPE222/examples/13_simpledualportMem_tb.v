`timescale 1ns / 1ps

module logic_gate_tb();

reg clk, reset, wr_en;
reg [15:0] dat_in;
reg [9:0] wr_adr;
reg [9:0] rd_adr;
wire [15:0] dat_out;

simpledpmem MEM1 (.clk(clk), .reset(reset), .dat_in(dat_in)
				,.wr_adr(wr_adr),.wr_en(wr_en),.dat_out(dat_out),.rd_adr(rd_adr));

initial begin
	clk = 0;
	wr_en = 0;
	dat_in = 7;
	wr_adr = 1;
	rd_adr = 1;
	#40
	wr_en = 1;
	dat_in = 7;
	wr_adr = 1;
	rd_adr = 1;
	#15
	wr_en = 0;
	rd_adr = 1;
	#13
	wr_en = 1;
	dat_in = 10;
	wr_adr = 1;
	rd_adr = 1;
	#15
	wr_en = 0;
	dat_in = 10;
	wr_adr = 1;
	rd_adr = 1;
end 

always begin 
    #10 clk = ~clk;
end 
 
initial begin
    $display(" clk dat_in wr_adr wr_en dat_out rd_adr");
    $monitor(" \t%b \t%b \t%b \t%b \t%b \t%b",
			clk, dat_in, wr_adr, wr_en, dat_out, rd_adr);
end

endmodule




















