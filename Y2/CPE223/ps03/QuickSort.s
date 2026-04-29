Data      DCD     10, 12, 8, 1, 5, 7, 11, 6, 8

          ;       call quicksort(Data, 0, 8)
          LDR     R0, =Data ; R0 = base address
          MOVS    R1, #0x00 ; left index
          MOVS    R2, #0x08 ; right index (9 elements -> last index 8)
          BL      QUICKSORT

          END

          ;       ------------------------------------------------------------
          ;       void QUICKSORT(int *base, int left, int right)
          ;       R0 = base pointer
          ;       R1 = left index
          ;       R2 = right index
          ;       ------------------------------------------------------------
QUICKSORT 
          CMP     R1, R2
          BPL     QS_RET ; if left >= right, return

          STMFD   SP!, {R4-R8, LR} ; save working regs + return
          MOV     R4, R0 ; keep base
          MOV     R5, R1 ; keep left
          MOV     R6, R2 ; keep right

          ;       partition(base, left, right) -> pivotIndex in R3
          BL      PARTITION
          MOV     R7, R3 ; pivot index

          ;       quicksort(base, left, pivotIndex-1)
          MOV     R0, R4
          MOV     R1, R5
          SUB     R2, R7, #0x01
          BL      QUICKSORT

          ;       quicksort(base, pivotIndex+1, right)
          MOV     R0, R4
          ADD     R1, R7, #0x01
          MOV     R2, R6
          BL      QUICKSORT

          LDMFD   SP!, {R4-R8, LR}
QS_RET    
          MOV     PC, LR

          ;       ------------------------------------------------------------
          ;       int PARTITION(int *base, int left, int right)
          ;       Lomuto partition:
          ;       pivot = A[right]
          ;       i = left - 1
          ;       for j in [left..right-1]:
          ;       if A[j] <= pivot: i++; swap(A[i], A[j])
          ;       swap(A[i+1], A[right]); return i+1
          ;
          ;       Inputs:
          ;       R0 = base pointer
          ;       R1 = left index
          ;       R2 = right index
          ;       Output:
          ;       R3 = pivot index
          ;       Clobbers:
          ;       R4-R12
          ;       ------------------------------------------------------------
PARTITION 
          STMFD   SP!, {R4-R12, LR}

          MOV     R4, R0 ; base
          MOV     R5, R1 ; left
          MOV     R6, R2 ; right

          ;       pivot = A[right]
          MOV     R8, R6
          LSL     R8, R8, #0x02 ; right * 4
          ADD     R8, R4, R8 ; &A[right]
          LDR     R7, [R8] ; pivot value

          ;       i = left - 1
          SUB     R9, R5, #0x01 ; i
          MOV     R10, R5 ; j = left

P_LOOP    
          CMP     R10, R6
          BPL     P_DONE ; j >= right -> finish

          ;       load A[j]
          MOV     R11, R10
          LSL     R11, R11, #0x02 ; j * 4
          ADD     R11, R4, R11 ; &A[j]
          LDR     R12, [R11] ; A[j]

          CMP     R12, R7
          BGT     P_NEXT ; if A[j] > pivot, no swap

          ;       i++
          ADD     R9, R9, #0x01

          ;       swap A[i] and A[j]
          MOV     R0, R9
          LSL     R0, R0, #0x02 ; i * 4
          ADD     R0, R4, R0 ; &A[i]
          LDR     R1, [R0] ; tmp = A[i]
          STR     R12, [R0] ; A[i] = A[j]
          STR     R1, [R11] ; A[j] = tmp

P_NEXT    
          ADD     R10, R10, #0x01 ; j++
          B       P_LOOP

P_DONE    
          ;       swap A[i+1] and A[right]
          ADD     R9, R9, #0x01 ; i+1

          MOV     R0, R9
          LSL     R0, R0, #0x02
          ADD     R0, R4, R0 ; &A[i+1]
          LDR     R1, [R0] ; tmp = A[i+1]
          STR     R7, [R0] ; A[i+1] = pivot
          STR     R1, [R8] ; A[right] = tmp

          MOV     R3, R9 ; return pivot index

          LDMFD   SP!, {R4-R12, LR}
          MOV     PC, LR
