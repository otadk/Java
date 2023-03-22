public class passingByReference {
    public static void main(String[] args) {
        var a = new Employee("a");
        var b = new Employee("b");
        System.out.println("Before: a=" + a.getName());
        System.out.println("Before: b=" + b.getName());
        // invalid swap
        swap1(a, b);
        // valid swap
        swap2(a, b);
        System.out.println("Before: a=" + a.getName());
        System.out.println("Before: b=" + b.getName());
    }

    private static class Employee {
        private String name;
        public Employee(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    private static void swap1(Employee a, Employee b) {
        Employee tmp = a;
        a = b;
        b = tmp;
    }

    private static void swap2(Employee a, Employee b) {
        String name = a.getName();
        a.setName(b.getName());
        b.setName(name);
    }
}
