import sys


def main():
    filename = sys.argv[1]
    classpath = sys.argv[2]
    entry = sys.argv[3]

    file_handle = open(filename, "w")
    file_handle.write("Main-Class: " + entry + "\n")
    file_handle.write(
        "Class-Path: " + classpath + "\n\n")
    file_handle.close()


if __name__ == "__main__":
    main()
