class Book implements Comparable<Book> {
  String title;
  int year;

  Book(String title, int year) {
    this.title = title;
    this.year = year;
  }

  @Override
  public int compareTo(Book other) {
    return Integer.compare(this.year, other.year);
  }

  @Override
  public String toString() {
    return title + "(" + year + ")";
  }
}
