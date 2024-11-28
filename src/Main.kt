// Абстрактний клас користувача (інкапсуляція)
abstract class Person(val id: String, val name: String) {
    open fun displayInfo() {
        println("ID: $id, Name: $name")
    }
}

// Успадкування для користувача спортзалу
class Member(id: String, name: String, val membershipType: String) : Person(id, name) {
    var checkInTime: String? = null  // Nullable тип

    override fun displayInfo() {
        super.displayInfo()
        println("Membership Type: $membershipType")
    }

    fun renewMembership() {
        println("Membership renewed for member: $name")
    }

    fun checkIn() {
        checkInTime = java.time.LocalDateTime.now().toString()
        println("$name checked into the gym at $checkInTime.")
    }
}

// Успадкування для тренера
class Instructor(id: String, name: String, val expertise: String) : Person(id, name) {
    fun scheduleTraining(sessionDetails: String) {
        println("$name scheduled training: $sessionDetails")
    }

    fun conductTraining() {
        println("$name is conducting training in $expertise.")
    }
}

// Колекція учасників спортзалу
class GymManagementSystem {
    private val members = mutableListOf<Member>()

    fun addMember(member: Member) {
        members.add(member)
        println("Member added: ${member.name}")
    }

    // Фільтрація членів за типом абонемента
    fun filterMembersByType(type: String): List<Member> {
        return members.filter { it.membershipType.equals(type, ignoreCase = true) }
    }

    // Сортування учасників за іменем
    fun sortMembersByName() {
        members.sortBy { it.name }
        println("Members sorted by name: ${members.joinToString { it.name }}")
    }

    // Пошук учасника за іменем (робота з рядками)
    fun findMemberByName(searchName: String): Member? {
        return members.find { it.name.equals(searchName, ignoreCase = true) }
    }
}

// Клас сесії доступу (з Nullable типами)
class Session {
    val sessionID: String = java.util.UUID.randomUUID().toString()
    val startTime: java.time.LocalDateTime = java.time.LocalDateTime.now()
    var endTime: java.time.LocalDateTime? = null

    fun extend(durationMinutes: Long) {
        endTime = (endTime ?: startTime).plusMinutes(durationMinutes)
        println("Session extended. New end time: $endTime")
    }
}

// Головна функція для тестування
fun main() {
    val gymSystem = GymManagementSystem()

    // Створення учасників
    val member1 = Member("M001", "John Doe", "Premium")
    val member2 = Member("M002", "Jane Smith", "Standard")
    val member3 = Member("M003", "Alice Johnson", "Premium")

    // Додавання учасників до системи
    gymSystem.addMember(member1)
    gymSystem.addMember(member2)
    gymSystem.addMember(member3)

    // Фільтрація за типом абонемента
    println("Premium members:")
    gymSystem.filterMembersByType("Premium").forEach { it.displayInfo() }

    // Сортування учасників за іменем
    gymSystem.sortMembersByName()

    // Пошук учасника за ім'ям
    val searchName = "Jane Smith"
    val foundMember = gymSystem.findMemberByName(searchName)
    if (foundMember != null) {
        println("Found member: ${foundMember.name}")
    } else {
        println("Member $searchName not found.")
    }

    // Робота з сесією та Nullable типами
    val session = Session()
    session.extend(60)
}
