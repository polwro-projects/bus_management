import sys


def main():
    filename = sys.argv[1]
    entry = sys.argv[len(sys.argv) - 1]

    file_handle = open(filename, "w")
    file_handle.write("Main-Class: " + entry + "\n")

    file_handle.write("Class-Path: ")
    for index in range(2, len(sys.argv) - 1):
        file_handle.write(sys.argv[index] + " ")
    file_handle.write("\n\n")

    file_handle.close()


if __name__ == "__main__":
    main()
