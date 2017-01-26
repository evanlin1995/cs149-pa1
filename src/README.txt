Programming Assignment #1 - ChatServer
======================================
Evan Lin - elin13
Malina Jiang - malinaj

The concurrent server uses a thread pool, locks, and conditional wait and notify to handle incoming requests in parallel.

The thread pool consists of a limited number of threads (8 for this assignment) as well as a linked list (queue) of tasks that can be assigned to the threads. While the task list is empty, the threads available in the pool wait, but as soon as a new task is assigned, a thread is notified and assigned to the task. Additionally, the tasks queue ensures that all tasks are assigned by priority of the time they were assigned. In the ChatServer, the thread pool is used to handle incoming requests. Upon accepting a connection, a new task is created and added to the thread pool task queue.

Only one lock is used for the ChatServer to limit access to the chat history variable, as multiple threads may attempt to access (either read or write) the history at the same time. During both reads and writes, the history is locked to prevent read-write and write-write corruption, and then released when the read or write is complete. The history lock may also be released when a reading thread waits for new messages. Each ChatState has its own copy of the history variable, and each history lock will be acquired and released similarly for concurrent requests.

Finally, the ChatServer employs conditional wait and notify to coordinate access to the history variable. Aside from the wait and notify in the thread pool class, if a reading thread encounters no new messages, it releases the lock to other requesting threads and waits up to 15 seconds. During that time, another thread may acquire access to history in order to read or write messages. If the thread writes to history, it will notify all threads waiting for the history lock, allowing reading threads to resume with the new messages or writing threads to modify history.