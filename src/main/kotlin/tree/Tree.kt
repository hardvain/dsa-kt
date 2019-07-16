package dev.aravindh.src.main.kotlin.tree

interface Node<T> {
    val parent: Node<T>?
    val children: List<Node<T>>
    val siblings: List<Node<T>>
    val degree: Int
    val isRoot: Boolean
    val isInternal: Boolean
    val isLeaf: Boolean
    val level: Int
    val depth: Int
    val height: Int
    fun deleteLeftChild()
    fun deleteRightChild()
}

interface Tree<T> {
    val width: Int
    val root: Node<T>?
    val degree: Int
    val depth: Int
    val height: Int

    fun insert(value: T)
    fun delete(value: T)
    fun search(value:T): Node<T>?
}