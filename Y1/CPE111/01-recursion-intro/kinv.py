def KInv(k):
    for i in range(1, 1001):
        if (k * i) % 26 == 1:
            return i
    return -1


if __name__ == "__main__":
    for i in range(1, 28):
        if KInv(i) != -1:
            print(f"{i} = {KInv(i)}")
