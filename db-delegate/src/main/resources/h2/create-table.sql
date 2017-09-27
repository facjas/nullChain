-- Create table
create table IF NOT EXISTS block
(
  hash       VARCHAR2(100) not null,
  height     BIGINT,
  createtime BIGINT,
  PRIMARY KEY (hash)
);
-- block  index
create unique index IF NOT EXISTS block_height_idx on block(height);