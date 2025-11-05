module counter (
    input clk_in,
    input sw,
    output reg [3:0] led
);

initial led = 0;

always @(posedge clk_in) begin
    if (sw) begin
        led <= 0;
    end else begin
        if (led == 4'd9) begin
            led <= 0;
        end else begin
            led <= led + 1;
        end
    end
end

endmodule
