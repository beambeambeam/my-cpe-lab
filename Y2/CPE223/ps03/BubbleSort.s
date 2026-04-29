Data    DCD     10, 12, 8, 1, 5, 7, 11, 6, 8
        MOVS    R12, #0x08
LOOP1   LDR     R0, =Data
        MOVS    R10, #0x00
LOOP    BL      SORT
        ADD     R0, R0, #0x04
        ADD     R10, R10, #0x01
        CMP     R10, R12
        BNE     LOOP
        SUB     R12, R12, #0x01
        CMP     R12, #0x01
        BNE     LOOP1
        END
SORT    LDR     R1, [R0]
        LDR     R2, [R0, #0x04]
        CMP     R2, R1
        BPL     NOTHING
        MOV     R11, R1
        MOV     R1, R2
        MOV     R2, R11
        STR     R1, [R0]
        STR     R2, [R0, #0x04]
NOTHING MOV     PC, LR

