cmake_minimum_required(VERSION 3.4.1)

set(SOURCES)

set(SOPHIA_PATH "${CMAKE_CURRENT_LIST_DIR}/sophia")

file(GLOB_RECURSE SOURCES "${CMAKE_CURRENT_LIST_DIR}/*.c")

include_directories(
    ${SOPHIA_PATH}/std
    ${SOPHIA_PATH}/format
    ${SOPHIA_PATH}/runtime
    ${SOPHIA_PATH}/object
    ${SOPHIA_PATH}/version
    ${SOPHIA_PATH}/wal
    ${SOPHIA_PATH}/database
    ${SOPHIA_PATH}/index
    ${SOPHIA_PATH}/transaction
    ${SOPHIA_PATH}/repository
    ${SOPHIA_PATH}/scheduler
    ${SOPHIA_PATH}/environment
    ${SOPHIA_PATH}/sophia
    ${CMAKE_CURRENT_LIST_DIR}/include
)

if(${ANDROID_ABI} STREQUAL "armeabi-v7a")
    include_directories(${ANDROID_SYSROOT}/usr/include/arm-linux-androideabi)
elseif(${ANDROID_ABI} STREQUAL "arm64-v8a")
    include_directories(${ANDROID_SYSROOT}/usr/include/aarch64-linux-android)
    SET(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -D__LP64__=1")
else()
    include_directories(${ANDROID_SYSROOT}/usr/include/arm-linux-androideabi)
    SET(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -D__mips__=1")
endif()

add_library(db_sophia SHARED ${SOURCES})

set(LIBS)
list(APPEND LIBS
    android
)

target_link_libraries(db_sophia ${LIBS})