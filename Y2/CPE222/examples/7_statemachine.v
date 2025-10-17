module StateMachine
(
    clk,
    reset,
    start,
    resume,
    pause,
    die,
    state_reg
);
 
    input       clk;
    input       reset;
    input       start;
    input       resume;
    input       pause;
    input       die;      
 
    output reg [1:0]   state_reg;
 
    // State Machine Parameter
    parameter game_running  = 2'b00;
    parameter game_paused   = 2'b01;
    parameter game_over     = 2'b10;
 
    //State Machine
    always @( posedge clk or posedge reset)
        begin
            if (reset)  
                state_reg <= game_running;
            else
                begin
                    case (state_reg)
                        game_running:
                            if (pause)      
                                state_reg <= game_paused;
                            else if (die)  
                                state_reg <= game_over;
   
                        game_paused:
                            if (resume)    
                                state_reg <= game_running;
   
                        game_over:
                            if (start)      
                                state_reg <= game_running;
   
                        default:            
                            state_reg <= game_running;
                    endcase
                end
        end
 
endmodule