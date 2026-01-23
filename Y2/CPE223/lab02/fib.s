.global main
.text

main:
    PUSH {LR}
    MOV R0, #0          @ a = 0
    MOV R1, #1          @ b = 1
    MOV R3, #2          @ i = 2

loop:
    CMP R3, #44
    BGT done
    ADD R2, R0, R1      @ temp = a + b
    MOV R0, R1          @ a = b
    MOV R1, R2          @ b = temp
    ADD R3, R3, #1      @ i++
    B loop

done:
    LDR R0, =format
    BL printf
    MOV R0, #0
    POP {PC}

.data
format: .asciz "44th Fibonacci number: %d\n"
