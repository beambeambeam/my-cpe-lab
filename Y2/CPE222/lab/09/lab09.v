module top(
    input wire [2:0] in,
    output wire y
);

wire and_out;

assign and_out = in[0] & in[1];
assign y = and_out | in[2];

endmodule
