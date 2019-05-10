package academy.elqoo.java8.stream;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers) {
        return numbers.stream()
                .map(u -> u * u).collect(Collectors.toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user) {
        return user.stream()
                .map(u -> u.getAge()).collect(Collectors.toList());


    }

    public static List<Integer> getDistinctAges(List<User> users) {
        return users.stream()
                .map(u -> u.getAge()).distinct().collect(Collectors.toList());


    }

    public static List<User> getLimitedUserList(List<User> users, int limit) {
        return users.stream()
                .limit(limit).collect(Collectors.toList());


    }

    public static Integer countUsersOlderThen25(List<User> users) {

        return Math.toIntExact(users.stream()
                .filter(u -> u.getAge() > 25).count());
    }

    public static List<String> mapToUpperCase(List<String> strings) {
        return strings.stream()
                .map(s -> s.toUpperCase()).collect(Collectors.toList());


    }

    public static Integer sum(List<Integer> integers) {
        return integers.stream().mapToInt(i -> i).sum();
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip) {
        return integers.stream()
                .skip(toSkip).collect(Collectors.toList());

    }

    public static List<String> getFirstNames(List<String> names) {
        return names.stream()
                .flatMap(string -> Stream.of(string.substring(0, string.indexOf(" ")))).collect(Collectors.toList());

    }


    public static List<String> getDistinctLetters(List<String> names) {
        return names.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }


    public static String separateNamesByComma(List<User> users) {
        return users.stream()
                .map(u -> u.getName()).collect(Collectors.joining(", "));

    }

    public static double getAverageAge(List<User> users) {

        return users.stream()
                .collect(Collectors.averagingInt(u -> u.getAge()));

    }

    public static Integer getMaxAge(List<User> users) {
        return users.stream().map(User::getAge)
                .max(Comparator.naturalOrder()).get();

    }

    public static Integer getMinAge(List<User> users) {
        return users.stream()
                .map(User::getAge).min(Comparator.naturalOrder()).get();
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users) {
        return users.stream()
                .collect(groupingBy(user -> user.isMale()));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users) {
        return users.stream()
                .collect(groupingBy(u -> u.getAge()));

    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users) {
        return users.stream().collect(groupingBy(u -> u.isMale(), groupingBy(u -> u.getAge())));
    }

    public static Map<Boolean, Long> countGender(List<User> users) {
        return users.stream()
                .collect(groupingBy((User u) -> u.isMale(), counting()));
    }

    public static boolean anyMatch(List<User> users, int age) {
        return users.stream()
                .anyMatch(user -> user.getAge() == age);

    }

    public static boolean noneMatch(List<User> users, int age) {
        return users.stream()
                .noneMatch(u -> u.getAge() == age);
    }

    public static Optional<User> findAny(List<User> users, String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name)).findAny();
    }

    public static List<User> sortByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream) {
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers() {
        return Stream.iterate(2, integer -> integer + 1)
                .filter(Stream8::isPrime)
                .limit(10)
                .collect(Collectors.toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }

    public static List<Integer> generate10RandomNumbers() {
        return Stream.generate(()->new Random(). nextInt()).limit(10).collect(Collectors.toList());
    }

    public static User findOldest(List<User> users) {
        return users.stream()
                .max(Comparator.comparing(user -> user.getAge())).get();
    }

    public static int sumAge(List<User> users) {
     return users.stream()
            .mapToInt(User::getAge).sum();

    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge).summaryStatistics();
    }

}
