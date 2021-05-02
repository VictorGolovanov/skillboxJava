/* Столкнулся с ошибкой:
  Exception in thread "main" org.hibernate.PropertyAccessException:
  Null value was assigned to a property [class Course.studentsCount] of primitive type setter of Course.studentsCount
  Оказалось, что у курса по SQL в скачанной мной базе данных students_count == NULL
  Поэтому использовал экземпляры классов, а не примитивы */

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // тут можно было бы оставить int,
                        // т.к. в базе данных установлен запрет на NULL для id, но все же

    private String name;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "students_count")
    private Integer studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private Float pricePerOur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Float getPricePerOur() {
        return pricePerOur;
    }

    public void setPricePerOur(Float pricePerOur) {
        this.pricePerOur = pricePerOur;
    }

}
