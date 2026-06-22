-- Database Schema Definitions for Legal AI-d
-- Complete SQL reference for Room SQLCipher encrypted database

-- API Configurations Table
CREATE TABLE IF NOT EXISTS api_configs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    provider_name TEXT NOT NULL UNIQUE,
    api_key_encrypted BLOB NOT NULL,
    base_url TEXT,
    is_active INTEGER DEFAULT 0,
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER DEFAULT (strftime('%s', 'now'))
);

-- Projects (Cases) Table
CREATE TABLE IF NOT EXISTS projects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    color_hex TEXT,
    is_archived INTEGER DEFAULT 0,
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER DEFAULT (strftime('%s', 'now'))
);

-- Documents Table - stores full raw text encrypted
CREATE TABLE IF NOT EXISTS documents (
    id TEXT PRIMARY KEY,
    project_id INTEGER NOT NULL,
    filename TEXT NOT NULL,
    mime_type TEXT NOT NULL,
    raw_text_encrypted BLOB NOT NULL,
    file_size INTEGER,
    page_count INTEGER,
    checksum TEXT,
    type TEXT DEFAULT 'LEGAL_BRIEF',
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

-- Messages Table
CREATE TABLE IF NOT EXISTS messages (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_id INTEGER NOT NULL,
    role TEXT NOT NULL CHECK (role IN ('user', 'assistant', 'system')),
    content TEXT NOT NULL,
    token_count INTEGER,
    model_used TEXT,
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

-- Skills Table
CREATE TABLE IF NOT EXISTS skills (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    description TEXT,
    instructions TEXT NOT NULL,
    tags TEXT DEFAULT '[]',
    is_built_in INTEGER DEFAULT 0,
    is_active INTEGER DEFAULT 1,
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    updated_at INTEGER DEFAULT (strftime('%s', 'now'))
);

-- Persistent Memory / Task Log Table
CREATE TABLE IF NOT EXISTS task_log (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_id INTEGER,
    task_type TEXT NOT NULL,
    task_description TEXT NOT NULL,
    context_references TEXT,
    outcome TEXT,
    importance_score REAL DEFAULT 0.0,
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    accessed_at INTEGER DEFAULT (strftime('%s', 'now'))
);

-- RAG Vector Database Metadata
CREATE TABLE IF NOT EXISTS rag_databases (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_id INTEGER NOT NULL,
    name TEXT NOT NULL,
    db_path TEXT NOT NULL,
    dimension INTEGER,
    is_active INTEGER DEFAULT 1,
    created_at INTEGER DEFAULT (strftime('%s', 'now')),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

-- RAG Chunks Table (for FTS5 fallback)
CREATE TABLE IF NOT EXISTS rag_chunks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    document_id INTEGER NOT NULL,
    chunk_text TEXT NOT NULL,
    chunk_index INTEGER NOT NULL,
    embedding_json TEXT,
    FOREIGN KEY (document_id) REFERENCES documents(id) ON DELETE CASCADE
);

-- SQLite FTS5 Virtual Table for full-text search
CREATE VIRTUAL TABLE IF NOT EXISTS documents_fts USING fts5(
    chunk_text,
    content='rag_chunks',
    content_rowid='id'
);

-- Chat Parameters (per project)
CREATE TABLE IF NOT EXISTS chat_parameters (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_id INTEGER NOT NULL UNIQUE,
    temperature REAL DEFAULT 0.7,
    top_p REAL DEFAULT 0.9,
    top_k INTEGER DEFAULT 40,
    frequency_penalty REAL DEFAULT 0.0,
    presence_penalty REAL DEFAULT 0.0,
    system_prompt TEXT,
    max_tokens INTEGER DEFAULT 4096,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);