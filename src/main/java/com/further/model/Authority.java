package com.further.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Authority {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long authorityId;

   @Column(name = "name", unique = true)
   private String name;

   public Authority() {}

   public Authority(String name) {
      this.name = name;
   }

   public Long getAuthorityId() {
      return authorityId;
   }

   public void setAuthorityId(Long authorityId) {
      this.authorityId = authorityId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Authority authority = (Authority) o;
      return Objects.equals(name, authority.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Authority{" +
         "authorityId=" + authorityId +
         ", name='" + name + '\'' +
         '}';
   }
}
