# javamysqldemo
Simple example that demonstrates use of the JDBC connectivity from Java to MySql Database

# MySQL DB structure for example
Database: `employee`

## Table structure for table `emp`
CREATE TABLE `emp` (
  `empno` int(11) NOT NULL,
  `empname` varchar(20) NOT NULL,
  `empdob` date NOT NULL,
  `empsalary` float NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

## AUTO_INCREMENT for table `emp`
ALTER TABLE `emp`
  MODIFY `empno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;

## Indexes for table `emp`
ALTER TABLE `emp`
  ADD PRIMARY KEY (`empno`);

## Insert some data into the table
INSERT INTO `emp` (`empno`, `empname`, `empdob`, `empsalary`) VALUES
(1, 'Stephen', '1980-03-14', 70000),
(2, 'John', '1990-03-15', 25000);
