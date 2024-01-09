FROM eclipse/ubuntu_jdk8

COPY target/gigo-0.0.1-SNAPSHOT.jar app.jar

# Expose port của ứng dụng
EXPOSE 8089

# Chỉ định command để chạy ứng dụng khi container khởi chạy
CMD ["java", "-jar", "app.jar"]