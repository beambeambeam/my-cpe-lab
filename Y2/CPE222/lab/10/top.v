module clk_div (
  input wire rst_ni,
  input wire clk_i,
  output wire clk_o
);
reg [31:0] counter_r;
reg clk_r;

always @(posedge clk_i) begin
  if (!rst_ni) begin
    clk_r <= 0;
    counter_r <= 0;
  end else begin
    if (counter_r == (1_000 / 2) - 1) begin
      clk_r <= ~clk_r;
      counter_r <= 0;
    end else begin
      counter_r <= counter_r + 1;
    end
  end
end

assign clk_o = clk_r;

endmodule
