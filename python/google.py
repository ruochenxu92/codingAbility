def searchRange(self, num, target):
    start = 0
    last = len(num) - 1
    while start <= last:
        mid = start + (last - start) / 2
        if num[mid] == target:
            start = mid
            while start - 1 >= 0 and num[start - 1] == target:
                start -= 1
            last = mid
            while last + 1 < len(num) and num[last + 1] == target:
                last += 1
            return [start, last]         
        elif num[mid] < target:
            start = mid + 1
        else:
            last = mid - 1
    return [-1, -1]
        
        
        
def minWindow(self, S, T):
    dict = {}
    for t in T:
        dict[t] = dict[t] + 1 if t in dict else 1
    start = 0
    minV = len(S) + 1
    minS = 0
    count = 0
    for i in range(len(S)):
        if S[i] in dict:
            dict[S[i]] -= 1
            count += 1 if dict[S[i]] >= 0
            if count == len(T):
                while count == len(T):
                    if S[start] not in dict:
                        start += 1
                    elif dict[S[start]] < 0:
                        dict[S[start]] += 1
                        start += 1
                    else:
                        break
                if i - start + 1 < minV:
                    minV = i - start + 1
                    minS = start
    return S[minS: minS + minV] if minV < len(S) + 1 else ''
                    
                 






        
        
        
def findSubstring(self, S, L):
    try:
        def check(s, L, length):
            if len(s) == length:
                dict = {}
                for i in range(len(L)):
                    dict[L[i]] += 1 if L[i] in dict else 1        
                for i in range(0, len(s), len(L[0])):
                    tmp = s[i:i + len(L[0])]
                    if tmp in dict and dict[tmp] > 0:
                        dict[tmp] -= 1
                        continue
                    else:
                        return False
                return True
            return False
        res = []
        length = len(L) * len(L[0])    
        for i in range(len(L[0])):
            for j in range(i, len(S), len(L[0])):
                if check(S[i:i + length], L, length):
                    res.append(i)
        return res
    except:
        return []
        
    
 
def longestConsecutive(self, A):
    dict = {}
    for num in A:
        dict[num] = 1
    maxV = 0
    for i in range(len(A)):
        if dict[A[i]] != 0:
            up = i
            dict[i] = 0
            while up + 1 in dict and dict[up + 1] != 0:
                up += 1
                dict[up] = 0
            low = i
            while low - 1 in dict and dict[low - 1] != 0:
                low -= 1
                dict[low] = 0
            maxV = max(maxV, up - low + 1)
    return maxV
       
                
            
            
            
def lengthOfLongestSubstring(self, s):
    visit = set()
    maxV = 0
    maxS = 0
    start = 0
    for i in range(len(s)):
        if s[i] not in visit:
            visit.add(s[i])
            if maxV < i - start + 1:
                maxS = start
                maxV = i - start + 1
            maxV = max(maxV, i - start + 1)
        else:
            for k in range(start, i):
                if s[k] == s[i]:
                    start = k + 1
                    break
                else:
                    visit.remove(s[k])
                    
    return s[maxS: maxS + maxV]
                
  







def addBinary(self, a, b):
    res = [0 for i in range(1 + max(len(a), len(b)))]
    for i in range(max(len(a), len(b))):
        if i < len(a):
            res[- 1 - i] += a[-1 - i]
        if i < len(b):
            res[- 1 - i] += b[-1 - i]
    cr = 0
    for i in reversed(range(len(res))):
        tmp = res[i] + cr
        res[i] = tmp % 10
        cr = tmp / 10   
    k = 0
    while k + 1 < len(res) -1 and res[k + 1] == 0:
        k += 1
    return ''.join([str(tmp) for tmp in res[k:]])
        
    
    
    
    def divide(self, dividend, divisor):
        
    
        if abs(dividend) < abs(divisor) or dividend == 0:
            return 0
        if divisor == 0:
            return -2 ** 31 if dividend < 0 else 2 ** 31 - 1
    
        a = abs(dividend); b = abs(divisor)
        shift = 0
        
        while (b << shift + 1) <= a:
            shift += 1
        res = 0
        while shilf >= 0:
            if a >= b << shift:
                a -= b << shift
                res += 1 <<shift
            shift -= 1
        
        if dividend < 0 and divisor > 0 or dividend > 0 and divisor < 0:
            res = 0 - res
        if res > 2 ** 31 - 1:
            return 2 ** 31 - 1
        if res < -2 ** 31:
            return - 2** 31
        return res

            
            
            
    def plusOne(self, digits):
        digits[-1] += 1
        
        cr = 0
        for i in reversed(range(len(digits))):
            tmp = digits[i] + cr
            cr = tmp / 10
            digits[i] = tmpp % 10
        
        return [cr] + digits if cr != 0 else digits 
        
            
        
    def reverse(self, x):
        if x < 0: return -self.reverse(-x)
        res = 0
        while x >= 0:
            res = res * 10 + x % 10
            x /= 10
        return res if res < 2 ** 31 else 0
        
   
    def romanToInt(self, s):
        dict = {}
        dict['M'] = 1000
        dict['D'] = 500
        dict['C'] = 100
        dict['L'] = 50
        dict['X'] = 10
        dict['V'] = 5
        dict['I'] = 1
        
        res = 0
        for i in range(len(s)):
            if i > 0 and dict[s[i]] > dict[s[i-1]]:
                res -= dict[s[i-1]] * 2
                res += dict[s[i]]
        return res
    
    
    
    
    
    def singleNumber(self, A):
        dict = {}
        for i in range(len(A)):
            dict[A[i]] = dict[A[i]] + 1 if A[i] in dict else 1
        
        for key in dict:
            if dict[key] == 1:
                return dict[key]
    
    
    


    def atoi(self, s):
        if not s:
            return 0
        s = s.strip()
        if not s:
            return 0
        sign = -1 if s[0] == '-' else 1
        start = 0
        start = start + 1 if s[0] == '+' or s[0] == '-' else start
       
        for i in range(start, len(s)):
            if '0' <= s[i] <= '9':
                res = res * 10 + int(s[i])
                if res * sign >= 2 ** 31 - 1: return 2 ** 31 - 1
                if res * sign =< - 2** 31: return -2 ** 31
            else:
                return res * sign
        return res * sign
        
        
        
        
    def isNumber(self, s):
        if not s or not s.strip():
            return False
        s = s.strip()

        num = False
        dot = False
        exp = False
        
        if s[0] in '+-':
            s = s[1:]
        
        for i in range(len(s)):
            
            if '0' <= s[i] <= '9':
                num = True
            elif s[i] == '.':
                if not num or exp:
                    return False                          
                dot = True
                num = False
            elif s[i] in 'eE':
                if not num:
                    return False
                exp = True
                num = False
            elif s[i] in '+-':
                if i > 0 and s[i - 1] in 'eE':
                    continue
                else:
                    return False
            else:
                    return False
        return num
                    
        def intToRoman(self, num):
        
            def convert(digit, one, five, ten):
                buf = ''
                if digit == 9:
                    return buf += one + ten
                elif 5 <= digit <= 8:
                    buf += five
                    buf += one * (digit - 5)
                elif digit == 4:
                    buf += one + five
                else:
                    buf += one * digit
                return buf
        
            res = ''
            res += convert(num / 1000, 'M','','')
            res += convert((num/100)%10,'C','D','M')
            res += convert((num/10) %10,'I','X','C')
            return res
        
        
        def isPalindrome(self, x):
            if x < 0: return False
            
            numofdigits = 0
            while x / (10 ** (numofdigits + 1)) > 0:
                numofdigits += 1
                           
            while x >= 0:
                first = (x / (10 ** numofdigits)) % 10
                last = x % 10
                if first != last:
                    return False
                x /= 100
                numofdigits -= 1
            return True                
            
            
            
            
            
            
        
        
            
            
            
            
            
            
            
            
    def trap(self, height):
        if not height: return 0
        res = 0
        
        maxIndex = 0
        maxV = height[0]
        for i in range(len(height)):
            if height[i] > maxV:
                maxV = height[i]
                maxIndex = i
       
        maxV = height[0]
        for i in range(maxIndex):
            maxV = max(maxV, height[i])
            res += maxV - height[i]
        
        maxV = height[-1]
        for i in reversed(range(maxIndex, len(height))):
            maxV = max(maxV, height[i])
            res += maxV - height[i]
        
        return res
            
            
        
        
    def maxArea(self, height):
        i = 0
        j = len(height) - 1
        maxV = 0
        while i < j:
            maxV = max(maxV, min(height[i], height[j]) * (j - i))
            if height[i] > height[j]:
                j -= 1
            else:
                i += 1
        return maxV
        
        
        
    def fullJustify(self, words, L):
        res = []    
        count = 0
        start = 0
        for i in range(len(words)):
            if size + len(words[i]) + 1 > L:
                buf = ''
                stops = i - start - 1
                numwords = stops + 1
                remain = L - count
                
                if numwords == 1:
                    buf += words[start]
                    buf += ' ' * remain
                else:
                    for j in range(numwords):
                        buf += words[start + j] + ' '  + ' ' * (remain / numwords)
                        if j == len(numwords) - 1:
                            buf += ' ' * (remain % numwords) 
                
                res.append(buf)
                count = 0
                start = i                                          
            else:
                size += len(words[i]) + 1
                count += len(words[i])
        
        stops = len(words) - start - 1
        numwords = stops + 1
        remain = L - count
        buf = ''
        if numwords == 1:
            buf += words[start]
            buf += ' ' * remain
        else:
            for j in range(numwords):
                buf += words[start + j] + ' ' + ' ' * (remain / numwords
                if j == len(numwords) - 1:
                    buf += ' ' * (remain % numwords)
        res.append(buf)
        return res
            
               
                
                
 

def isPalindrome(self, s):
    if not s:
        return False
    
    
    i = 0
    j = len(s) - 1
    
    while i < j:
        
        if not s[i].isalnum():
            i += 1
        elif not s[j].isalnum():
            j -= 1
        elif s[i] != s[j]:
            return False
        else:
            i += 1
            j -= 1
    return True
        
        
    def flatten(self, root):
        if not root: return
        
        self.flatten(root.left)
        self.flatten(root.right)
        tmp = root.right
        root.right = root.left
        root.left = None
        trav = root
        while trav.right:
            trav = trav.right
        trav.right = tmp
        
            
        
                

def pathSum(self, root, sum):
    def dfs(root, my_sum, sol, res):
        if root:
            my_sum -= root.val
            new_sol = sol + [root.val]
            if not root.left and not root.right and not my_sum:
                res.append(new_sol)
                return
            dfs(root.left, my_sum, new_sol, res)
            dfs(root.right, my_sum, new_sol, res)
    res = []
    dfs(root, sum, [], res)
    return res
    
    
    
    
    
    def connect(self, root):
        if root:
            if root.left:
                root.left.next = root.right
                if root.next:
                    root.right.next = root.next.left
                self.connect(root.right)
                self.connect(root.left)
                
                
            
            



    def connect(self, root):    
        def findNext(root):
            while root:
                if root.left: return root.left
                if root.right: return root.right
                root = root.next
            return None
        
        if root:
            if root.right:
                root.right.next = findNext(root.next)
            if root.left:
                root.left.next = root.right if root.right else findNext(root.next)
            self.connect(root.right)
            self.connect(root.left)                
        
        
        
    def isBalanced(self, root):
        def height(self, root):
            if not root: return 0
            return max(height(root.left), height(root.right)) + 1
        
        if not root: return True
        if abs(height(root.left) - height(root.right)) <= 1:
            return self.isBalanced(root.left) and self.isBalanced(root.right)
        return False
        
        
        
    
    def inorderTraversal(self, root):
        if not root: return []
        res = []
        stack = [root]
        leftmost = None
        while leftmost or stack:
            if leftmost:
                stack.append(leftmost)
            else:
                node = stack.pop()
                res.append(node.val)
                leftmost = node.right
        return res
        
        
        
        
    def levelOrder(self, root):
        if not root: return []
        q = [root]
        
        res = []
        while q:
            size = len(q)
            tmp = []
            for i in range(size):
                cur = q.pop(0)
                tmp.append(cur.val)
                if cur.left:
                    q.append(cur.left)
                if cur.right:    
                    q.append(cur.right)    
            res.append(tmp)
        return res
                
           
                
                
        
    def postorderTraversal(self, root):
        if not root: return []
        stack = [root]
        visit = set()
        res = []
        
        while stack:
            cur = stack.pop()
            if cur in visit:
                res.append(cur.val)
            else:
                visit.add(cur)
                stack.append(cur)
                if cur.right:
                    stack.append(right)
                if cur.left:    
                    stack.append(left)
        return res
          
          
          
    def preorderTraversal(self, root):
        if not root: return []
        res = []
        stack = [root]
        
        while stack:
            cur = stack.pop()
            res.append(cur.val)
            if cur.right:
                stack.append(cur.right)
            if cur.left:
                stack.append(cur.left)
        return res
                

    def zigzagLevelOrder(self, root):
        if not root: return []
        res = []
        q = [root]
        odd = True
        
        while q:
            size = len(q)
            tmp = []
            for i in range(size):
                cur = q.pop(0)
                if cur.left:
                    q.append(cur.left)
                if cur.right:
                    q.append(cur.right)
            
            if not odd:
                tmp.reverse()
            odd = not odd
            res.append(tmp)
        return res
           
           
    
     def construct(self, inorder, postorder, istart, pstart, length):
            if length == 0:
                return None
            root = TreeNode(postorder[pstart + length - 1])
            if length == 1:
                return root
            
            mid = inorder[istart:istart + length].index(root.val)
            
            leftlength = mid - istart
            rightlength = length - 1 - leftlength
            root.left = self.construct(inorder, postorder, istart, pstart, leftlength) 
            root.right = self.construct(inorder, postorder, mid + 1, pstart + leftlength, rightlength) 
            return root   
    
       
           
    def buildTree(self, inorder, postorder):
        return self.construct(inorder, postorder, 0, 0, len(inorder))
        
        
       


    def sortedArrayToBST(self, num):
        mid = len(num) / 2    
        
        root = TreeNode(num[mid])
        
        root.left = self.sortedArrayToBST(num[:mid])
        root.right = self.sortedArrayToBST(num[mid + 1:])
        return rot
        
        
    def maxDepth(self, root):
        if not root: return 0
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) +1 
            
                           
    def minDepth(self, root):
        if not root: return 0
        if not root.left and not root.right:
            return 1
        elif root.left and root.right:
            return min(self.minDepth(root.left),self.mindDepth( root.right)) + 1
        elif root.left:
            return self.minDepth(root.left) + 1
        elif root.right:
            return self.minDepth(root.right) + 1
            


    def recoverTree(self, root):
        def findTwoNodes(root, prev):
            if not root: return
                findTwoNodes(root.left, prev)
                if prev and prev.val < root.val:
                    if not self.first: self.first = prev
                    else: self.sec = root
                findTwoNodes(root.right, root)
        self.first = None
        self.sec = None
        findTwoNodes(root, None)
        self.first.val, self.sec.val = self.sec.val, self.first.val

                    
                      
    def isSameTree(self, p, q):
        if not p and not q:
            return True
        if p and q and p.val == q.val:
            return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
        return False
                          
        
    def isValidBST(self, root):
        if not root: return True
        if root.left and root.right:
            if root.right.val > root.val > root.left.val:
                return self.isValidBST(root.left) and self.isValidBST(root.right)
            return False
        elif root.left and root.left.val < root.right.val:
            return self.isValidBST(root.left)
        elif root.right and root.right.val > root.left.val:
            return self.isValidBST(root.right)
        return False
          
        
        
        
    def canCompleteCircuit(self, gas, cost):
        total = 0
        sum = 0
        start = 0
        for i in range(len(gas)):
            if sum < 0:
                sum = 0
                start = i
            sum += gas[i] - cost[i]
            total += gas[i] - cost[i]
        if total < 0: return -1
        return start
        
    
    def strStr(self, haystack, needle):
        if not needle: return 0
        for i in range(len(haystack) - len(needle) + 1):
            if haystack[i:i + len(needle)] == needle: return True
        return False
            

    def longestCommonPrefix(self, strs):
        if not strs: return ''
        model = strs[0]
        for i in range(len(model)):
            for string in strs:
                if i == len(string) or string[i] != model[i]:
                    return model[:i]
        return True 
        
        
        
    def evalRPN(self, tokens):
        if not tokens: return 0
        stack = []
        
        for i in range(len(tokens)):
            if tokens[i] in '+-*/':
                n2 = stack.pop()
                n1 = stack.pop()
                if tokens[i] == '+': stack.append(n1 + n2)
                if tokens[i] == '-': stack.append(n1 - n2)
                if tokens[i] == '*': stack.append(n1 * n2)
                if tokens[i] == '/': 
                    if n1 * n2 > 0:
                        stack.append(n1 / n2)
                    else:
                        stack.append(-(-n1/n2))
             else:
                 stack.append(int(tokens[i]))
         return stack.pop()
         
                    
                                


def largestRectangleArea(self, height):
    maxV = 0
    stack = []
    
    for i in range(len(height)):
        while stack and height[i] < height[stack[-1]]:
            last = stack.pop() 
            start = stack[-1] + 1 if stack else: 0
            maxV = max(maxV, height[last] * (i - start))
        stack.append(i)
    
    while stack:
        last = stack.pop()
        start = stack[-1] + 1 if stack else: 0
        maxV = max(maxV, height[last] * (len(height) - start)
    return 
        
            




def longestValidParentheses(self, s):
    stack = []
    preS = -1
    maxV = 0
    for i in range(len(s)):
        if s[i] == '(':
            stack.append(i)
        else:
            if not stack:
                preS = i
            else:
                stack.pop()
                if stack: maxV = max(maxV, i - stack[-1])
                else: maxV = max(maxV, i - preS)
    return maxV
            








def simplifyPath(self, path):
    stack = []
    dirs = path.split('/')
    for dir in dirs:
        if dir == '..':
            if stack: stack.pop()
        elif dir == '.' or dir == '':
            continue
        else:
            stack.append(dir)
    buf = ''
    buf += '/' + '/'.join(stack)
    return buf
        



def isValid(self, s):
    stack = []
    
    for p in s:
        if p in '([{':
            stack.append(p)
        else:
            if not stack: return False
            q = stack.pop()
            if q + p in '()[]{}':
                continue
            else: return False
    
    return True if not stack else False





def candy(self, ratings):
    scores = [1 for i in range(len(ratings))]
    
    for i in range(len(ratings)):
        if i > 0 and ratings[i] > ratings[i - 1]:
            scores[i] = scores[i - 1] + 1
    
    for i in reversed(range(len(ratings))):
        if i < len(ratings) - 1 and ratings[i] > ratings[i + 1]:
            scores[i] = max(scores[i], scores[i + 1] + 1)
    return sum(scores)
        


def removeDuplicates(self, A):
    j = 0
    for i in range(1, len(A)):
        if A[i] != A[j]:
            A[j + 1] = A[i]    
            j += 1
    return j + 1


def removeDuplicates(self, A)
    if not A: return 0
    j = 0
    
    for i in range(len(A)):
        if A{i] != A[j]:
            A[j + 1] = A[i]
            j += 1
            dup = 0
        elif dup < 1:
            dup += 1
            A[j + 1] = A[i]
            j += 1
    return j + 1

def removeElement(self, A, elem):
    j = 0
    for i in range(len(A)):
        if A[i] != elem:
            A[j] = A[i]
            j += 1
    return j
    

def mergeKLists(self, lists):
    heap = []
    
    for node in lists:
        if node:
            heap.append((node.val, node))
    heapq.heapify(heap)
    
    res = []
    dummy = ListNode(-1)
    tail = dummy
    while heap:
        cur = heapq.heappop(heap)
        tail.next = cur[1]
        tail = tail.next
        if cur[1].next:
            heapq.heappush(heap, (cur[1].next.val, cur[1].next)
    return dummy.next
        

def merge(self, A, m, B, n):
    last = m + n - 1
    
    i = m - 1
    j = n - 1
    while i and j:
        if A[i] > B[j]: 
            A[last] = A[i]
            last -= 1
            i -= 1
        else:
            A[last] = B[j]
            last -= 1
            j -= 1

    while j:
        A[last] = B[j]
        last -= 1
        j -= 1
        
        
            
def sortColors(self, A):
    dict = {}
    
    for num in A:
        dict[num] = dict[num] + 1 if num in dict else 1

    j = 0    
    for i in range(3):
        while dict[i] > 0:
            dict[i] -= 1
            A[j] = i
            j += 1
    
            

def pow(self, x, n):
    if n == 0:
        return 1.0
    if n < 0:
        return 1 / self.pow(x, -n)
    else:
        if n % 2 == 1:
            return self.pow(x * x, n / 2) * x
        else:
            return self.pow(x * x, n / 2)