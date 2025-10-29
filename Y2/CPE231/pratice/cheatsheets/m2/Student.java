class Student {
  String name;
  double gpa;
  int id;

  Student(String name, double gpa, int id) {
    this.name = name;
    this.gpa = gpa;
    this.id = id;
  }

  @Override
  public String toString() {
    return name + "(GPA:" + gpa + ",ID:" + id + ")";
  }
}
